def generate_summator_truthtable():
    quantity_of_arguments = 3
    arguments = generate_argument_dictionary(quantity_of_arguments)
    b = [0] * len(arguments['x1'])
    d = b[:]

    for i in range(len(arguments['x1'])):
        total_sum = arguments['x1'][i] + arguments['x2'][i] + arguments['x3'][i]
        if total_sum >= 2:
            b[i] = 1
            total_sum -= 2
        if total_sum == 1:
            d[i] = 1

    return d, b


def transform_matrix_to_string(formula):
    arguments_length = 3
    inside_operator = '*'
    outside_operator = '+'
    substring = []

    for row in formula:
        arguments = []
        for j, value in enumerate(row):
            if value == 0:
                arguments.append('!x' + str(j + 1))
            if value == 1:
                arguments.append('x' + str(j + 1))
        substring.append(inside_operator.join(arguments))
        if len(substring[-1]) > arguments_length:
            substring[-1] = '(' + substring[-1] + ')'

    output = outside_operator.join(substring)
    return output


def transform_truthtable_to_PNDF(truthtable, quantity_of_arguments):
    formula = []
    arguments = generate_argument_dictionary(quantity_of_arguments)
    for i in range(len(truthtable)):
        if truthtable[i] == 1:
            bracket = [arguments['x' + str(arg_index)][i] for arg_index in range(1, quantity_of_arguments + 1)]
            formula.append(bracket)
    return formula


def generate_argument_dictionary(quantity_of_arguments):
    dictionary = {}
    for i in range(quantity_of_arguments):
        index = i + 1
        same = 2 ** (quantity_of_arguments - index)
        array = [0] * same + [1] * same
        array *= 2 ** (index - 1)
        dictionary['x' + str(index)] = array
    return dictionary


def delete_excess_parts(formula):
    new_formula = formula.copy()
    no_change = 1
    i = 0
    while i < len(new_formula):
        result = []
        for other_term in new_formula:
            if new_formula[i] != other_term:
                sub = substitute_in_formula(new_formula[i], other_term)
                if sub[1] == no_change:
                    result.append(sub[0])
        positive, negative = False, False
        for argument in result:
            if argument == 0: negative = True
            if argument == 1: positive = True
        if positive and negative:
            new_formula.pop(i)
        else:
            i += 1
    return new_formula


def is_mergable_by_argument(first_constituent, second_constituent, argument_index, quantity_of_arguments):
    for i in range(quantity_of_arguments):
        if i != argument_index and first_constituent[i] != second_constituent[i]:
            return False
        if i == argument_index and first_constituent[i] == second_constituent[i]:
            return False
    return True


def simplify(formula):
    quantity_of_arguments = len(formula[0])
    i = quantity_of_arguments
    simplified_formula = []
    merged = formula
    while i > 1:
        merged, unmerged = merge(merged, quantity_of_arguments)
        merged = delete_excess_parts(merged)
        simplified_formula.extend(unmerged)
        i -= 1
    simplified_formula.extend(merged)
    simplified_formula = delete_identical_element(simplified_formula)
    return simplified_formula


def substitute_in_formula(values, formula):
    missed_value = None
    existing_argument = None

    for i, value in enumerate(values):
        if value == -1:
            missed_value = i
        elif existing_argument is None:
            existing_argument = i

    result = [formula[missed_value]]
    if formula[existing_argument] == values[existing_argument]:
        result.append(1)
    else:
        result.append(0)

    return result


def merge(formula, quantity_of_arguments):
    merged_part = []
    unmerged_part = []
    used = [False] * len(formula)
    for i in range(quantity_of_arguments):
        for j in range(len(formula) - 1):
            for k in range(j + 1, len(formula)):
                if is_mergable_by_argument(formula[j], formula[k], i, quantity_of_arguments):
                    used[j] = True
                    used[k] = True
                    merged_formula = formula[j][:]
                    merged_formula[i] = -1
                    merged_part.append(merged_formula)
                    break
    for i, is_used in enumerate(used):
        if not is_used:
            unmerged_part.append(formula[i])
    return merged_part, unmerged_part


def delete_identical_element(formula):
    unique_formula = []
    for item in formula:
        if item not in unique_formula:
            unique_formula.append(item)
    return unique_formula


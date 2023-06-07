import Perfect_NDF


def summator_table():
    quantity_of_arguments = 3
    arguments = Perfect_NDF.generate_argument_dictionary(quantity_of_arguments)
    b = [0] * len(arguments['x1'])
    d = b.copy()

    for i in range(len(arguments['x1'])):
        total_sum = sum(arguments[arg][i] for arg in arguments.keys())

        if total_sum >= 2:
            b[i] = 1
            total_sum -= 2

        if total_sum == 1:
            d[i] = 1

    return d, b


def tetrad_plus_five():
    quantity_of_arguments = 4
    arguments = Perfect_NDF.generate_argument_dictionary(quantity_of_arguments)
    five_bin = [0, 1, 0, 1]
    five_dec = 5
    y = [[0] * len(arguments['x1']) for _ in range(quantity_of_arguments)]

    for i in range(len(arguments['x1']) - five_dec):
        for index in range(quantity_of_arguments, 0, -1):
            total_sum = arguments['x' + str(index)][i] + five_bin[index - 1]
            y[index - 1][i] = total_sum % 2

    return y


print("The first task")
print("1-bit binary adder for 3 inputs:\n")

arguments_number = 3
d, b = summator_table()
arguments = Perfect_NDF.generate_argument_dictionary(arguments_number)

print("x1: " + " ".join(map(str, arguments["x1"])))
print("x2: " + " ".join(map(str, arguments["x2"])))
print("x3: " + " ".join(map(str, arguments["x3"])))
print("d:  " + " ".join(map(str, d)))
print("b:  " + " ".join(map(str, b)))

d_PNDF = Perfect_NDF.transform_truthtable_to_PNDF(d, arguments_number)
print("\nPNDF(d): " + Perfect_NDF.transform_matrix_to_string(d_PNDF))
d_simplified = Perfect_NDF.simplify(d_PNDF)
d_simplified = Perfect_NDF.transform_matrix_to_string(d_simplified)
print("TNDF(d): " + d_simplified)

b_PNDF = Perfect_NDF.transform_truthtable_to_PNDF(b, arguments_number)
print("\nPNDF(d): " + Perfect_NDF.transform_matrix_to_string(b_PNDF))
b_simplified = Perfect_NDF.simplify(b_PNDF)
b_simplified = Perfect_NDF.transform_matrix_to_string(b_simplified)
print("TNDF(d): " + b_simplified)

print("\nThe second task:")
print("n = 5\n")

arguments_number = 4
arguments = Perfect_NDF.generate_argument_dictionary(arguments_number)

for i in range(arguments_number):
    print("x" + str(i + 1) + ": " + " ".join(map(str, arguments["x" + str(i + 1)])))

result = tetrad_plus_five()
print("-----------------------------------")

for i in range(arguments_number):
    print("y" + str(i + 1) + ": " + " ".join(map(str, result[i])))

for i in range(arguments_number):
    y_PNDF = Perfect_NDF.transform_truthtable_to_PNDF(result[i], arguments_number)
    print("\nPNDF(y" + str(i + 1) + "): " + Perfect_NDF.transform_matrix_to_string(y_PNDF))
    y_simplified = Perfect_NDF.simplify(y_PNDF)
    y_simplified = Perfect_NDF.transform_matrix_to_string(y_simplified)
    print("TNDF(y" + str(i + 1) + "): " + y_simplified)


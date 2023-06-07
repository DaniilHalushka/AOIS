import Perfect_NDF

V_index = 3
arguments_number = 3


def transition_table():
    arguments = Perfect_NDF.generate_argument_dictionary(arguments_number + 1)
    table = []
    for q in arguments.values():
        table.append(q)
    table += current_q(arguments)
    h = [[0 for m in range(len(arguments['x1']))] for n in range(arguments_number)]
    for i in range(len(h)):
        for j in range(len(h[i])):
            if table[i][j] != table[i + V_index + 1][j]:
                h[i][j] = 1
    table += h
    return table


def current_q(arguments):
    arguments_number = 3
    q = [[0 for m in range(len(arguments['x1']))] for n in range(arguments_number)]
    for i in range(len(arguments['x1'])):
        V = [0 for j in range(arguments_number - 1)] + [arguments['x' + str(arguments_number + 1)][i]]
        index = arguments_number
        plusone = 0
        while index > 0:
            sum = arguments['x' + str(index)][i] + V[index - 1] + plusone
            plusone = 0
            if sum >= 2:
                sum -= 2
                plusone = 1
            q[index - 1][i] = sum
            index -= 1
    return q


table = transition_table()
string_names = ['q3*', 'q2*', 'q1*', 'V', 'q3', 'q2', 'q1', 'h3', 'h2', 'h1']
print('Двоичный счетчик накапливающего типа на 8 внутренних состояний в базисе НЕ И-ИЛИ и Т-триггер')
for i in range(len(table)):
    print(string_names[i].ljust(5) + ' '.join([str(el) for el in table[i]]))

h_index = 7
for i in range(h_index, len(table)):
    h_pdnf = Perfect_NDF.transform_truthtable_to_PNDF(table[i], arguments_number + 1)
    h_simplified = Perfect_NDF.simplify(h_pdnf)
    h_pdnf = Perfect_NDF.transform_matrix_to_string(h_pdnf)
    h_pdnf = h_pdnf.replace('x', 'q')
    h_pdnf = h_pdnf.replace('q4', 'V')
    h_pdnf = h_pdnf.replace('1', '4')
    h_pdnf = h_pdnf.replace('3', '1')
    h_pdnf = h_pdnf.replace('4', '3')
    h_simplified = Perfect_NDF.transform_matrix_to_string(h_simplified)
    h_simplified = h_simplified.replace('x', 'q')
    h_simplified = h_simplified.replace('q4', 'V')
    h_simplified = h_simplified.replace('1', '4')
    h_simplified = h_simplified.replace('3', '1')
    h_simplified = h_simplified.replace('4', '3')
    print('\nPDNF(h' + str(len(table) - i) + '): ' + h_pdnf)
    print('PDNF(h' + str(len(table) - i) + '): ' + h_simplified)

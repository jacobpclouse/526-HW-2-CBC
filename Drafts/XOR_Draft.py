# Draft of XOR function

from numpy import number


number1 = input("Give me the first number to XOR: ")
number2 = input("Give me the second number to XOR: ")

number1 = int(number1)
number2 = int(number2)


print(f"Number 1: {number1} - Binary: {bin(number1)}")
print(f"Number 2: {number2} - Binary: {bin(number2)}")

XOR_output = number1 ^ number2

print(f"XOR output: {XOR_output} - Binary Output {bin(XOR_output)}")


# I don't think this is working properly, we need to double check

# Python3 code to demonstrate working of
# Converting binary to string
# Using BinarytoDecimal(binary)+chr()
  

from lib2to3.pytree import convert


def binaryConvert(test_str):
    # initializing string
    #test_str = "GeeksforGeeks"
    
    # printing original string
    print("The original string is : " + str(test_str))
    
    # using join() + bytearray() + format()
    # Converting String to binary
    res = ''.join(format(i, '08b') for i in bytearray(test_str, encoding ='utf-8'))
    output = str(res)
    # printing result
    print("The string after binary conversion : " + str(res))
    return output

# Defining BinarytoDecimal() function
def BinaryToDecimal(binary):
     
    # Using int function to convert to
    # string  
    string = int(binary, 2)
     
    return string
     
# Driver's code
# initializing binary data

def return_to_String(bin_data):
    #bin_data ='0100011101100101011001010110101101110011'
    # print binary data
    print("The binary value is:", bin_data)
    
    # initializing a empty string for
    # storing the string data
    str_data =' '
    
    # slicing the input and converting it
    # in decimal and then converting it in string
    for i in range(0, len(bin_data), 7):
        
        # slicing the bin_data from index range [0, 6]
        # and storing it in temp_data
        temp_data = bin_data[i:i + 7]
        
        # passing temp_data in BinarytoDecimal() function
        # to get decimal value of corresponding temp_data
        decimal_data = BinaryToDecimal(temp_data)
        
        # Decoding the decimal value returned by
        # BinarytoDecimal() function, using chr()
        # function which return the string corresponding
        # character for given ASCII value, and store it
        # in str_data
        str_data = str_data + chr(decimal_data)
    
    # printing the result
    print("The Binary value after string conversion is:",
        str_data)

    return str_data


## Main:

print("Start String")

toConvert = input("What To convert: ")

converted = binaryConvert(toConvert)

returned = return_to_String(converted)
def binaryConvert(test_str):
    # initializing string
    #test_str = "GeeksforGeeks"
    
    # printing original string
    print("The original string is : " + str(test_str))
    
    # using join() + bytearray() + format()
    # Converting String to binary
    res = ''.join(format(i, '08b') for i in bytearray(test_str, encoding ='utf-8'))
    
    # printing result
    print("The string after binary conversion : " + str(res))


###


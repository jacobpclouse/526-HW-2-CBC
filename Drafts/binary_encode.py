def to_binary(string):
    #string = "Jake"
    # Encode the string into bytes using utf-8 encoding
    bytes = string.encode('utf-8')

    # Convert each byte to its binary representation
    binary = [bin(byte)[2:].zfill(8) for byte in bytes]

    # Join the binary representations of each byte into a single string
    binary_string = ''.join(binary)

    print(binary_string)
    return(binary_string)


def to_string_from_bin(binary_string):
#binary_string = "0100100001100101011011000110110001101111001000000111011101101111011100100110110001100100"

    # Split the binary string into a list of 8-bit binary representations
    binary_list = [binary_string[i:i+8] for i in range(0, len(binary_string), 8)]

    # Convert each binary representation to its decimal equivalent
    decimal_list = [int(binary, 2) for binary in binary_list]

    # Convert the sequence of decimal values back to the original string
    byte_string = bytes(decimal_list)
    original_string = byte_string.decode('utf-8')

    print(original_string)
    return original_string


converted = to_binary("Jake")
reverted = to_string_from_bin(converted)

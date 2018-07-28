#!/usr/bin/python3
'''
Simple compare script
'''


def validate(number: str) -> bool:
    '''Validate entered number string is valid number'''
    if number == '':
        print('Error: number is empty')
        return False
    try:
        int(number)
    except ValueError as exp:
        print('Error: ', exp)
        return False
    return True


def read_number(prefix: str) -> int:
    '''Read number from the prompt'''
    while True:
        prompt = 'Enter ' + prefix + ' > '
        resp = input(prompt)
        if not validate(resp):
            continue
        number = int(resp)
        break
    return number


def compare(a, b: int) -> (str, int):
    '''
    Compare two numbers
    It returns 1 if a > b,
       returns 0 if a == b,
       returns -1 if a < b,
       returns 255 if unknown error
    '''
    if a > b:
        return '>', 1
    elif a == b:
        return '==', 0
    elif a < b:
        return '<', -1
    return 'Unknown error', 255


def introduction():
    '''Display introduction with example'''
    _, example1 = compare(5, 2)
    _, example2 = compare(2, 5)
    _, example3 = compare(3, 3)
    print('''
Please input two numbers. They will be compared and return a number.

Example:
a > b  is {}
a < b  is {}
a == b is {}
 
    '''.format(example1, example2, example3))


def main():
    '''Read numbers from user input, then compare them, and return result'''
    introduction()
    first = read_number('a')
    second = read_number('b')
    result_str, result_int = compare(first, second)
    if result_int == 255:
        print(result_str)
        return
    print('Result: {} {} {} is {}'.format(first, result_str, second,
                                          result_int))


if __name__ == "__main__":
    main()

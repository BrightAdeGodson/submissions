#!/usr/bin/python3
'''
Simple calculator
Entered numbers are NOT supported floating-point
'''


def validate(number: str) -> bool:
    '''Validate entered number string is valid number'''
    if number == '':
        print('Error: number is empty')
        return False
    if not number.isdigit():
        print('Error: invalid number')
        return False
    return True


def read_number(prefix: str) -> int:
    '''Read number from the prompt'''
    while True:
        prompt = 'Enter ' + prefix + ' number > '
        resp = input(prompt)
        if not validate(resp):
            continue
        number = int(resp)
        if number != 0:
            break
        print('Error: zero is not allowed')
    return number


def read_operator() -> int:
    '''Read operator number from the prompt'''
    while True:
        print('1-add, 2-subtract, 3-multiply, 4-divide')
        resp = input('Enter operator number > ')
        if not validate(resp):
            continue
        operator = int(resp)
        if 0 < operator < 5:
            break
        print('Error: invalid operator number')
    return operator


def calc(first, operator_num, second: int) -> (str, int):
    '''
    Calculation two numbers using entered operator number
    It returns operator a character and a result
    '''
    if operator_num == 1:
        return '+', first + second
    elif operator_num == 2:
        return '-', first - second
    elif operator_num == 3:
        return '*', first * second
    return '/', first / second


def main():
    '''Display result of calculation'''
    first = read_number('1st')
    operator_num = read_operator()
    second = read_number('2nd')
    operator, result = calc(first, operator_num, second)
    print('Result: {} {} {} = {}'.format(first, operator, second, result))


if __name__ == "__main__":
    main()

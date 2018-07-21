#!/usr/bin/python3
"""
Print 25 new lines
"""


def new_line():
    """Print a new line"""
    print()


def three_lines():
    """
    Print 3 new lines using new_line()
    new_line() * 3 = 3 new lines
    """
    new_line()
    new_line()
    new_line()


def nine_lines() -> str:
    """
    Print 9 new lines using three_lines()
    three_lines() * 3 = 9 new lines
    """
    print('now printing 9 lines')
    for _ in range(3):
        three_lines()


def clear_screen():
    """
    Print 25 new lines using nine_lines(), three_lines() and new_line()
    nine_lines() * 2 = 18 new lines
    three_lines() * 2 = 6 new lines
    new_line() = 1 new line
    18 + 6 + 1 = 25 new lines
    """
    print('now printing 25 lines')
    for _ in range(2):
        nine_lines()
    for _ in range(2):
        three_lines()
    new_line()
    return


clear_screen()

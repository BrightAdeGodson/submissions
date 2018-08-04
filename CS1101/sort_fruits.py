#!/usr/bin/python3
'''
Sorting contents
'''

# Constants
UNSORTED_FILE = 'unsorted_fruits.txt'
SORTED_FILE = 'sorted_fruits.txt'


def main():
    '''Read contents from unsorted_fruits.txt, and write sorted fruits to sorted_fruits.txt'''
    fruits = []
    try:
        # with statement doesn't need to intentionally close()
        # Ref: http://preshing.com/20110920/the-python-with-statement-by-example/
        with open(UNSORTED_FILE, 'r') as infile:
            for line in infile.readlines():
                # Skip if line is only LF
                if line == '\n':
                    continue
                fruits.append(line)
    except EnvironmentError:
        print('Could not read file:', UNSORTED_FILE)
        return
    print(fruits)
    fruits.sort()
    print(fruits)
    try:
        # Add CRLF as newline for Windows user
        with open(SORTED_FILE, 'w', newline='\r\n') as outfile:
            for line in fruits:
                outfile.write(line)
    except EnvironmentError:
        print('Could not write file:', SORTED_FILE)


if __name__ == "__main__":
    main()

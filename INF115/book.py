
"""
Implement the add, get and remove commands.
"""

def add(input):
    with open("book.txt","a+") as b:
        b.write(input.partition(' ')[2] + "\n")

def get(input):
    with open("book.txt","r+") as b:
        rows = len(b.readlines())
        for r in range(rows):
            if(input == b.readline(r).split(" ")[0]):
                print(b.readline(r))


if __name__ == '__main__':
    
    print("Welcome to the phonebook!")
    
    running = True
    while running:
        command = input('> ')
        command, *data = command.split()
        
        if command == 'exit':
            running = False
        elif command == 'add':
            add(command)
        elif command == 'get':
            get(command)
        else:
            print(f'Command "{command}" not implemented.')




        
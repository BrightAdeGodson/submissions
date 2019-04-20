#include <stdio.h>
#include <unistd.h>
#include <sys/types.h>

int main()
{
    int x = 100;

    pid_t pid = fork();

    if (pid < 0)
    {
        perror("failed to create process");
    }
    else if (pid == 0)
    {
        printf("child process: before x = %d\n", x);
        x = 200;
        printf("child process: new x = %d\n", x);
    }
    else
    {
        printf("parent process: before x = %d\n", x);
        x = 300;
        printf("parent process: new x = %d\n", x);
    }

    printf("finally: x = %d\n", x);

    return 0;
}

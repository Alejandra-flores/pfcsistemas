#include <stdlib.h>
#include <stdio.h>

int exp(int i, int n)
/* calcula i a la potencia n */
{
  int ans, j;
  ans = 1;
  for (j=1; j <= n; j++) ans *= i;
  return(ans);
}

int main()
{
  int n, total, x, y, z;
  scanf("%d",&n);
  total = 3;
  while(1){
    for(x=1; x <= total - 2; x++)
      for(y=1; y <= total -x -1; y++)
	{
	  z = total - x - y;
	  if(exp(x,n) + exp(y,n) == exp(z,n))
	    printf("hola, mundo\n");
	}
    total++;
  }
}


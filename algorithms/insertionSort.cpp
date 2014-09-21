#include <vector>
#include <iostream>
#include <stdlib.h>

std::vector<int> myArr(10);

int main()
{
  int x;
  int y;
  int tmp;
  int steps = 0;

  for(x = 0; x < myArr.size(); x++)
  {
    myArr[x] = rand() % 11;
    std::cout << myArr[x] << " ";
  }

  std::cout << "\n";

  for(x = 1; x < myArr.size(); x++)
  {
    tmp = myArr[x];
    for(y = x; y > 0 && tmp < myArr[y - 1]; y--)
    {
      myArr[y] = myArr[y - 1];
    }
    myArr[y] = tmp;

    for(int i = 0; i < myArr.size(); i++)
    {
      std::cout << myArr[i] << " ";
    }

    std::cout << "\n";
  }


  return 0;
}

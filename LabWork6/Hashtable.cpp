#include <iostream>
#include <string.h>
#include "LibraryForHashTable.h"

using namespace std;

const int INITIALIZATION = 0;

struct HashTable
{
	string Key = "";
	string Info = "";
};

HashTable* Hash;

void Init(int Num) {
	Hash = new HashTable[Num];
}

int main()
{
	int numberOfElements = INITIALIZATION, Choose, position = INITIALIZATION;
	string stringForKey = "", stringForInformation = "";
	cout << "Enter the maximum number of elements: " << endl;
	cin >> numberOfElements;
	while (numberOfElements <= INITIALIZATION) {
		cout << "Enter the maximum number of elements (number > 0):\n";
		cin >> numberOfElements;
	}
	Init(numberOfElements);
	while (1)
	{
		cout << " 1-Insert element \n 2-Delete element \n 3-Find element \n 4-Exit \n ";
		cin >> Choose;
		switch (Choose)
		{
		case 1:
			cout << "Enter key \n";
			cin >> stringForKey;
			cout << "Enter info \n";
			cin >> stringForInformation;
			Add(Hash, stringForKey, stringForInformation);
			break;
		case 2:
			cout << "Enter key to delete element\n";
			cin >> stringForKey;
			Delete(Hash, stringForKey);
			break;
		case 3:
			cout << "Enter key to find element\n";
			cin >> stringForKey;
			View(Hash, stringForKey);
			break;
		case 4:
			exit(true);
			break;
		default:
			cout << "Try again" << endl;
			break;
		}
	}
	return 0;
}


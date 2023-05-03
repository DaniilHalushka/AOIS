#include <iostream>
#include <string>
#include "LibraryForHashTable.h"

using namespace std;

const int NOT_FOUND_VALUE = -1;
const int INITIALIZATION = 0;
const int UTIL_VALUE_FOR_HASH = 10000;

struct HashTable
{
	string Key = "";
	string Info = "";
};


string Delete(HashTable* hashValue, string key)
{
	int numberInHashTable;
	numberInHashTable = Find(hashValue, key);
	if (numberInHashTable != -1)
	{
		hashValue[numberInHashTable].Key = "";
		hashValue[numberInHashTable].Info = "";
	
		cout << "Deleted" << endl;
	}
	else
		cout << "Not find" << endl;
	return hashValue[numberInHashTable].Key;
}

int Find(HashTable* hashValue, string key)
{
	int numberInHashTable = INITIALIZATION;
	int i = INITIALIZATION;
	while (i < UTIL_VALUE_FOR_HASH)
	{
		numberInHashTable = (HashFunction1(key) + i * HashFunction2(key)) % 50 / 2;
		if (hashValue[numberInHashTable].Key == key) break;
		else numberInHashTable = NOT_FOUND_VALUE;
		i++;
	}
	return numberInHashTable;
}

void View(HashTable* hashValue, string key)
{
	int numberInHashTable = Find(hashValue, key);
	if (numberInHashTable != NOT_FOUND_VALUE)
		cout << hashValue[numberInHashTable].Info << endl;
	else
		cout << "Not find" << endl;
}

string Add(HashTable* hashValue, string key, string info)
{
	int numberInHashTable = INITIALIZATION;
	int i = INITIALIZATION;
	while (i < UTIL_VALUE_FOR_HASH)
		{
		numberInHashTable = (HashFunction1(key) + i * HashFunction2(key)) % 50 / 2;
			if (hashValue[numberInHashTable].Key == "")
			{
				hashValue[numberInHashTable].Info = info;
				hashValue[numberInHashTable].Key = key;
				cout << "Added sucsessfully" << endl;
				break;
			}
			i++;
		}
	return hashValue[numberInHashTable].Key;
}

int HashFunction1(string stringForHash)
{
	int key = INITIALIZATION;
	for (int i = INITIALIZATION; i < stringForHash.length(); i++)
	{
		key += (int)stringForHash[i] * (stringForHash.length() - i);
	}
	return key;
}

int HashFunction2(string stringForHash)
{
	int key = INITIALIZATION;
	for (int i = INITIALIZATION; i < stringForHash.length(); i++)
	{
		key += (int)stringForHash[i] * 2 / (i + 1);
	}
	return key;
}
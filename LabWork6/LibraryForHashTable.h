#pragma once
#include <string>

using namespace std;

#ifndef hsh_H
#define hsh_H

struct HashTable;
void View(HashTable* hashValue, string key);
string Add(HashTable* hashValue,string key, string info);
int Find(HashTable* hashValue, string key);
int HashFunction1(string stringForHash);
int HashFunction2(string stringForHash);
string Delete(HashTable* hashValue, string key);

#endif
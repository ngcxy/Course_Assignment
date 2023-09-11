// Name: Xinyi Cai
// USC NetID: xcai6647
// CSCI 455 PA5
// Spring 2023

// pa5list.cpp
// a program to test the linked list code necessary for a hash table chain

// You are not required to submit this program for pa5.

// We gave you this starter file for it so you don't have to figure
// out the #include stuff.  The code that's being tested will be in
// listFuncs.cpp, which uses the header file listFuncs.h

// The pa5 Makefile includes a rule that compiles these two modules
// into one executable.

#include <iostream>
#include <string>
#include <cassert>

using namespace std;

#include "listFuncs.h"


void testEmpty() {
   cout << "------------testEmpty----------------" << endl;
   ListType empty;
   initList(empty);
   cout << "an empty list: ";
   printList(empty);
   cout << endl;
   cout << "find a in empty list: " << find(empty, "a") << endl;
   cout << "remove a from empth list " << removeKey(empty, "a") << endl;
   cout << "has a? " << hasKey(empty, "a") << endl;
}

void testInsertFront(){
   cout << "------------testInsertFront----------------" << endl;
   ListType list;
   initList(list);
   cout << "Empty List:" ;
   printList(list);
   
   cout << "Insert a-1: " << insertFront(list, "a", 1) << endl;
   printList(list);
   cout << "Insert b-2: " << insertFront(list, "b", 2) << endl;
   printList(list);
   cout << "Insert c-3: " << insertFront(list, "c", 3) << endl;
   printList(list);
   cout << "Insert a-4: " << insertFront(list, "a", 4) << endl;
   printList(list);
}

void testFind(){
   cout << "------------testFind----------------" << endl;
   ListType list;
   initList(list);
   insertFront(list, "a", 1);
   insertFront(list, "b", 2);
   insertFront(list, "c", 3);
   cout << "List:" << endl;
   printList(list);
   cout << "find a : " << find(list, "a") << endl;
   cout << "find b : " << find(list, "b") << endl;
   cout << "find d : " << find(list, "d") << endl;
}


void testHasKey(){
   cout << "------------testHasKey----------------" << endl;
   ListType list;
   initList(list);
   insertFront(list, "a", 1);
   insertFront(list, "b", 2);
   insertFront(list, "c", 3);
   cout << "List:" << endl;
   printList(list);
   cout << "has a? : " << hasKey(list, "a") << endl;
   cout << "has b? : " << hasKey(list, "b") << endl;
   cout << "has d? : " << hasKey(list, "d") << endl;
}

void testGetNum(){
   cout << "------------testGetNum----------------" << endl;
   ListType list;
   initList(list);
   cout << "Empty List: ";
   printList(list);
   cout << "list num: " << getNum(list) << endl;
   insertFront(list, "a", 1);
   insertFront(list, "b", 2);
   cout << "List: " << endl;
   printList(list);
   cout << "list num: " << getNum(list) << endl;
}

void testRemove(){
   cout << "------------testRemove----------------" << endl;
   ListType list;
   initList(list);
   insertFront(list, "a", 1);
   insertFront(list, "b", 2);
   insertFront(list, "c", 3);
   cout << "List:" << endl;
   printList(list);
   cout << "Remove a : " << removeKey(list, "a") << endl;
   cout << "List: ";
   printList(list);
   cout << "Remove c : " << removeKey(list, "c") << endl;
   cout << "List: ";
   printList(list);
   cout << "Remove d : " << removeKey(list, "d") << endl;
   cout << "List: ";
   printList(list);
}


int main() {

   testEmpty();
   
   testInsertFront();
   
   testHasKey();
   
   testFind();

   testGetNum();
   
   testRemove();

   return 1;
}

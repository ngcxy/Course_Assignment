// Name: Xinyi Cai
// USC NetID: xcai6647
// CSCI 455 PA5
// Spring 2023


//*************************************************************************
// Node class definition 
// and declarations for functions on ListType

// Note: we don't need Node in Table.h
// because it's used by the Table class; not by any Table client code.

// Note2: it's good practice to *not* put "using" statement in *header* files.  Thus
// here, things from std libary appear as, for example, std::string

#ifndef LIST_FUNCS_H
#define LIST_FUNCS_H

#include <string>
  

struct Node {
   std::string key;
   int value;

   Node *next;

   Node(const std::string &theKey, int theValue);

   Node(const std::string &theKey, int theValue, Node *n);
};


typedef Node * ListType;

//*************************************************************************
//add function headers (aka, function prototypes) for your functions
//that operate on a list here (i.e., each includes a parameter of type
//ListType or ListType&).  No function definitions go in this file.


// initiate an empty list
void initList(ListType &list);

// insert a Node into list from front, return false if key already exist
// return True if the key is inserted, False if the key already exist
bool insertFront(ListType &list, const std::string & key, int value);

// return True if the list contains the key, False if not
bool hasKey(ListType & list, const  std::string & key);

// find the key in the list
// return the pointer to that node
ListType find(ListType & list, const std::string & key);

// remove the node by key from list
// return false iff key is not contained
bool removeKey(ListType & list, const  std::string & key);

// print the all element in the list in a form: 
// key1 value1
// key2 value2
void printList(const ListType & list);

// return the number of pairs in the list
int getNum(const ListType list);



// keep the following line at the end of the file
#endif

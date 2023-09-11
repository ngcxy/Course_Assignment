// Name: Xinyi Cai
// USC NetID: xcai6647
// CSCI 455 PA5
// Spring 2023


#include <iostream>

#include <cassert>

#include "listFuncs.h"

using namespace std;

Node::Node(const string &theKey, int theValue) {
   key = theKey;
   value = theValue;
   next = NULL;
}

Node::Node(const string &theKey, int theValue, Node *n) {
   key = theKey;
   value = theValue;
   next = n;
}

//*************************************************************************
// put the function definitions for your list functions below

// initiate an empty list
void initList(ListType &list) {
   list = NULL;
}

// insert a Node into list from front, return false if key already exist
// return True if the key is inserted, False if the key already exist
bool insertFront(ListType &list, const string & key, int value) {
   if (hasKey(list, key)) {
      return false;
   }
   list = new Node(key, value, list);
   return true;
}


// return True if the list contains the key, False if not
bool hasKey(ListType & list, const string & key) {
   ListType p = list;
   while (p != NULL) {
      if (p->key == key) {
          return true;
      }
      p = p->next;
   }
   return false;
}


// find the key in the list
// return the pointer to that node
ListType find(ListType & list, const string & key) {
   if (!hasKey(list, key)) {
      return NULL;
   }
   else if (list == NULL) {
      return NULL;
   }
   else {
      ListType p = list;
      while (p != NULL) {
         if (p->key == key) {
            return p;
         }
        p = p->next;
      }
   }
   return NULL;
}


// remove the node by key from list
// return false iff key is not contained
bool removeKey(ListType & list, const string & key) {
   ListType p = list;
   ListType previous = NULL;

   while (p != NULL) {
      if (p->key == key) {
         if (previous == NULL){
            list = list->next;
         }
         else {
            previous->next = p->next;
            delete p;
         }
         return true;
      }
      previous = p;
      p = p->next;
   }
   return false;
}

// print the all element in the list in a form: 
// key1 value1
// key2 value2
void printList(const ListType & list) {
   if (list != NULL) {
      ListType p = list;
      while (p->next != NULL) {
         cout << p->key << " " << p->value << endl;
         p = p->next;
      }
      cout << p->key << " " << p->value << endl;
   }
   else (cout << endl);
}

// return the number of pairs in the list
int getNum(const ListType list) {
   if (list == NULL) {
      return 0;
   }
   ListType p = list;
   int num = 1;
   while (p->next != NULL) {
      num++;
      p = p->next;
   }
   return num;
}
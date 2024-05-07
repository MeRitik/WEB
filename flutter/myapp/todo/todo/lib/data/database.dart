import 'package:hive_flutter/hive_flutter.dart';

class ToDoDatabase {
  List toDoList = [];
  final _myBox = Hive.box('myList');

  // If app has opened for first time
  void createInitialList() {
    toDoList = [
      ["Drink Cola", false],
      ["Ee sala cup namde", true],
      ["Play cricket", false]
    ];
  }

  // load the data from database
  void loadData() {
    toDoList = _myBox.get('TODO');
  }

  void updateDatabase() {
    _myBox.put('TODO', toDoList);
  }
}

// ignore_for_file: prefer_const_constructors

import 'package:flutter/material.dart';

class Home extends StatefulWidget {
  const Home({super.key});

  @override
  State<Home> createState() => _HomeState();
}

class _HomeState extends State<Home> {
  int _count = 0;
  void _incrementCount() {
    setState(() {
      ++_count;
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        body: Center(
      child: Column(
        mainAxisAlignment: MainAxisAlignment.center,
        children: [
          Text("You pressed the button this many times."),
          Text(
            _count.toString(),
            style: TextStyle(
              fontSize: 30,
            ),
          ),
          ElevatedButton(
            onPressed: _incrementCount,
            child: Text("Increment"),
          )
        ],
      ),
    ));
  }
}

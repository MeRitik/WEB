// ignore_for_file: prefer_const_constructors

import 'package:flutter/material.dart';

class Todo extends StatefulWidget {
  const Todo({super.key});

  @override
  State<Todo> createState() => _TodoState();
}

class _TodoState extends State<Todo> {
  TextEditingController textController = TextEditingController();
  String greetingMessage = "";

  void _getInput() {
    setState(() {
      greetingMessage = "Hello, ${textController.text}";
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Center(
        child: Padding(
          padding: EdgeInsets.all(25),
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: [
              // Greeting message
              Text(greetingMessage),
              TextField(
                controller: textController,
                decoration: InputDecoration(
                    border: OutlineInputBorder(), hintText: "Enter username."),
              ),
              Padding(
                padding: EdgeInsets.all(25),
                child: ElevatedButton(
                    onPressed: _getInput, child: Text("get username")),
              )
            ],
          ),
        ),
      ),
    );
  }
}

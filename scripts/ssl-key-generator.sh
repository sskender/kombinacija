#!/bin/bash

openssl req -x509 -nodes -days 365 -newkey rsa:2048 -keyout ../frontend/ssl/selfsigned.key -out frontend/ssl/selfsigned.crt
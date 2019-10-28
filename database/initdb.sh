#!/bin/sh

# exit on error
set -e

# env
export PGUSER="$POSTGRES_USER"


# adjust settings for server


# create user
psql -c "CREATE kombinacijauser WITH PASSWORD 'kombinacijapassword';"

# create database
psql -c "CREATE DATABASE kombinacijadb;"

# access to database
psql -c "REVOKE CONNECT ON DATABASE kombinacijadb FROM PUBLIC;"
psql -c "GRANT CONNECT ON DATABASE kombinacijadb TO kombinacijauser;"

# access to schema
psql -c "REVOKE ALL ON SCHEMA public FROM PUBLIC;"
psql -c "GRANT USAGE ON SCHEMA public TO kombinacijauser;"

# access to tables
psql -c "GRANT ALL ON ALL TABLES IN SCHEMA public TO kombinacijauser;"
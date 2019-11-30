#!/bin/sh

# exit on error
set -e

# env
export PGUSER="$POSTGRES_USER"


# adjust performance settings for server


# create user
psql -c "CREATE USER $DB_USER WITH PASSWORD '$DB_PASS';"

# create database
psql -c "CREATE DATABASE $DB_NAME;"

# access to database
psql -c "REVOKE CONNECT ON DATABASE $DB_NAME FROM PUBLIC;"
psql -c "GRANT CONNECT ON DATABASE $DB_NAME TO $DB_USER;"

# access to schema
psql -c "REVOKE ALL ON SCHEMA public FROM PUBLIC;"
psql -c "GRANT USAGE ON SCHEMA public TO $DB_USER;"

# access to tables
psql -c "GRANT ALL ON ALL TABLES IN SCHEMA public TO $DB_USER;"
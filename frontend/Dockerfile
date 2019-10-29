FROM nginx:latest

WORKDIR /etc/nginx

# global config
RUN mv nginx.conf nginx.conf.bkp
COPY nginx/nginx.conf nginx.conf

# website config
RUN mv conf.d/default.conf conf.d/default.conf.bkp
COPY nginx/kombinacija.conf conf.d/kombinacija.conf

# website data
COPY public /usr/share/nginx/html

EXPOSE 80
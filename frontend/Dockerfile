FROM nginx:latest

RUN rm -rf /etc/nginx/conf.d/*.conf

COPY nginx/kombinacija-web.conf /etc/nginx/conf.d/kombinacija-web.conf

# website directory
RUN mkdir -p /kombinacija/html
COPY public /kombinacija/html

EXPOSE 80
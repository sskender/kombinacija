FROM nginx:latest

RUN rm -rf /etc/nginx/conf.d/*.conf
COPY nginx/kombinacija-proxy.conf /etc/nginx/conf.d/kombinacija-proxy.conf

EXPOSE 80
FROM nginx:latest

# replace old conf
RUN rm -rf /etc/nginx/conf.d/*.conf
COPY nginx/kombinacija.conf /etc/nginx/conf.d/kombinacija.conf

# copy website data
RUN mkdir -p /kombinacija/html
COPY public /kombinacija/html

EXPOSE 80
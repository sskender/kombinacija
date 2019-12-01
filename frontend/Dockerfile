FROM nginx:latest

ENV DOMAIN_NAME=kombinacija.hopto.org

# replace old conf
RUN rm -rf /etc/nginx/conf.d/*.conf
COPY nginx/kombinacija.conf /etc/nginx/conf.d/kombinacija.conf

# copy website data
RUN mkdir -p /kombinacija/html
COPY public /kombinacija/html

# replace production url
RUN sed --in-place -E "s/localhost\:8080/$DOMAIN_NAME\/api\/v1/g" /kombinacija/html/js/server.api.js

EXPOSE 80
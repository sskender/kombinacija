FROM nginx:latest

ENV DOMAIN_NAME=kombinacija.hopto.org

# copy .conf files
RUN rm -rf /etc/nginx/conf.d/*.conf
COPY nginx/kombinacija.conf /etc/nginx/conf.d/kombinacija.conf

RUN mkdir -p /etc/nginx/conf/kombinacija
COPY nginx/cache-rules.conf /etc/nginx/conf/kombinacija/cache-rules.conf

# copy website data
RUN mkdir -p /kombinacija/html
COPY public /kombinacija/html

# replace production url
RUN sed --in-place -E "s/localhost\:8080/$DOMAIN_NAME\/api\/v1/g" /kombinacija/html/js/server.api.js

EXPOSE 80
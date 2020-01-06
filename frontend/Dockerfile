FROM nginx:latest


ENV DOMAIN_NAME=kombinacija.hopto.org


# copy nginx .conf files
RUN rm -rf /etc/nginx/conf.d/*.conf
COPY nginx/kombinacija.conf /etc/nginx/conf.d/kombinacija.conf

RUN mkdir -p /etc/nginx/conf/kombinacija
COPY nginx/cache-rules.conf /etc/nginx/conf/kombinacija/cache-rules.conf


# copy website data
RUN mkdir -p /kombinacija/html

COPY public /kombinacija/html


# create ssl directory
RUN mkdir -p /kombinacija/ssl/{cert,priv}

COPY ssl/*.crt /kombinacija/ssl/cert/
COPY ssl/*.key /kombinacija/ssl/priv/


# change server to match domain name
RUN sed --in-place -E "s/http/https/g" /kombinacija/html/js/server.api.js
RUN sed --in-place -E "s/localhost\:8080/$DOMAIN_NAME\/api\/v1/g" /kombinacija/html/js/server.api.js
RUN sed --in-place -E "s/localhost/$DOMAIN_NAME/g" /etc/nginx/conf.d/kombinacija.conf



EXPOSE 80
EXPOSE 443
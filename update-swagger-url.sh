#!/bin/bash

# Detecta a porta que o Spring Boot está usando (ajuste se necessário)
PORT=$(lsof -i -P -n | grep LISTEN | grep java | awk '{print $9}' | cut -d':' -f2 | head -n 1)

# Monta a URL com base no padrão do GitHub Codespaces
BASE_URL="https://friendly-space-cod-pjrwr664qwqvf6659-${PORT}.app.github.dev"

# Caminho para o application.properties
PROPERTIES_FILE="src/main/resources/application.properties"

# Atualiza a linha do swagger.server.url
if grep -q "^swagger.server.url=" "$PROPERTIES_FILE"; then
  sed -i "s|^swagger.server.url=.*|swagger.server.url=${BASE_URL}|" "$PROPERTIES_FILE"
else
  echo "swagger.server.url=${BASE_URL}" >> "$PROPERTIES_FILE"
fi

echo "✅ Swagger URL atualizada para: ${BASE_URL}"

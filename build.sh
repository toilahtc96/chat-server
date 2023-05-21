#export EZYFOX_SERVER_HOME=
mvn -pl . clean install
mvn -pl chat-server-common -Pexport clean install
mvn -pl chat-server-app-api -Pexport clean install
mvn -pl chat-server-app-entry -Pexport clean install
mvn -pl chat-server-plugin -Pexport clean install
cp chat-server-zone-settings.xml $EZYFOX_SERVER_HOME/settings/zones/

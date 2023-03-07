# serveur_image

## Pré-requis

Vous aurez besoin de :
- Node.js
- Vue.js
- Vite.js
- Maven (last version)
- npm (last version), Java 17.

Installation des modules le cas d'une machine Ubuntu :

```
# Mettre à jour votre système :
sudo apt update

# Installer npm :

sudo apt install npm

# Mettre à jour la version npm :
npm install -g npm@latest

# Installer Node.js :
npm install -g n

# Mettre à jour Node.js :
sudo n stable

# Installer Vue.js :
npm install vue

# Installer Vite.js :
npm install -g vite

# Installer Java 17 :
sudo apt install openjdk-17-jdk openjdk-17-jre

# Installer dernière version de Maven :
# Aller sur le site officiel de maven afin de récuperer l'url de téléchargement adéquat à votre système d'exploitation.

wget [VOTRE_URL

# Décompresser le fichier :
sudo tar -xf [LE_NOM_DU_FICHIER_COMPRESSE

# Configurer les variables d'environnement afin que Maven soit utilisable :
sudo nano /etc/profile.d/maven.sh

# Et ajouter dedans (modifier ce qu'il y a entre crochet) :
export M2_HOME=/opt/apache-maven-[VERSION_NUMBER
export PATH=${M2_HOME}/bin:${PATH}

# Recharger les variables d'environnement : 
source /etc/profile.d/maven.sh
```

## Préparation du serveur :

Aller dans le sous dossier front-end afin de tout initialiser comme il faut : 

```cd frontend/```

Taper la commande : 

```npm install```

##Lancement du serveur :

#### Revener à la racine du projet et taper :

```mvn clean```

Puis : 

```mvn clean install```

Si tout s'est bien passé vous pourrez lancer le serveur avec la commande suivante : 

```mvn --projects backend spring-boot:run```

Rendez-vous sur votre navigateur en tapant l'adresse suivante : 

```https://localhost:8080```

Si jamais l'adresse est pas bonne regarder le canal utlisé dans le terminal.




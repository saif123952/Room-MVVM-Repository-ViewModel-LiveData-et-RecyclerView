# Rapport de Laboratoire : Architecture MVVM avec Room

Ce projet implémente une application de gestion de tâches (Registry) en utilisant les composants d'architecture Android modernes. L'objectif était de mettre en œuvre une séparation claire des responsabilités en suivant le pattern **MVVM** (Model-View-ViewModel).

## Architecture du Projet

Le projet est structuré pour assurer la modularité et la facilité de maintenance :

- **Modèle (Persistence)** : 
    - `TaskEntry` : Représente l'entité de la base de données.
    - `TaskAccess` (DAO) : Définit les opérations d'accès aux données (Insert, Delete, Query).
    - `AppDatabase` : Gère l'instance de la base de données Room.
- **Logique (Repository & ViewModel)** :
    - `TaskRepository` : Abstraction de la source de données, gérant les opérations asynchrones via un `ExecutorService`.
    - `TaskViewModel` : Fournit les données à l'UI et survit aux changements de configuration (comme la rotation).
- **Vue (UI & Display)** :
    - `MainActivity` : Gère l'affichage et les interactions utilisateur.
    - `TaskAdapter` : Gère l'affichage efficace de la liste via un `RecyclerView`.

## Fonctionnalités

1. **Ajout de tâches** : Saisie d'un en-tête et d'un corps de message.
2. **Affichage en temps réel** : Utilisation de `LiveData` pour mettre à jour la liste automatiquement dès que la base de données change.
3. **Suppression individuelle** : Appui long sur un élément pour le supprimer.
4. **Réinitialisation** : Bouton pour vider l'intégralité de la base de données.
5. **Persistance** : Les données sont sauvegardées localement via SQLite.

## Aperçu de l'interface

<img width="362" height="820" alt="image" src="https://github.com/user-attachments/assets/78867439-d5a6-4756-b900-f283f7f72fda" />


## Technologies Utilisées

- **Room** : Pour la persistance des données.
- **ViewModel & LiveData** : Pour la gestion des données liées au cycle de vie.
- **RecyclerView & CardView** : Pour une interface fluide et moderne.
- **Material Design** : Pour les composants de saisie et les boutons.

---

## Commandes Git pour soumettre le projet

Voici les étapes pour initialiser votre dépôt et envoyer le code sur GitHub :

```bash
# Initialiser le dépôt local
git init

# Ajouter tous les fichiers du projet (incluant le README)
git add .

# Créer le premier commit
git commit -m "Initial commit: App Registry with MVVM and Room"

# Renommer la branche principale
git branch -M main

# Lier au dépôt distant (GitHub)
git remote add origin https://github.com/saif123952/Room-MVVM-Repository-ViewModel-LiveData-et-RecyclerView.git

# Envoyer le code
git push -u origin main
```

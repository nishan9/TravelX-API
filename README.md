<p><a target="_blank" href="https://app.eraser.io/workspace/FZOdoDP7gyF1Ceo5SjWs" id="edit-in-eraser-github-link"><img alt="Edit in Eraser" src="https://firebasestorage.googleapis.com/v0/b/second-petal-295822.appspot.com/o/images%2Fgithub%2FOpen%20in%20Eraser.svg?alt=media&amp;token=968381c8-a7e7-472a-8ed6-4a6626da5501"></a></p>

# Installation
Before running the application. 

1. Login to the azure portal. 
2. Go to the network tab in the SQL Server instance and add your IPv4 address, so you can connect to the server from your device. 
Run the application via `./mvnw spring-boot:run` in the home directory.

# Authorisation Flow
The following diagram shows how the application authorisation flow works:

![Authorisation Flow](/.eraser/FZOdoDP7gyF1Ceo5SjWs___U6y9emfrl8eHDv3I72P3GpFUUJa2___---figure---oBjVNl6M2yDtFaNPqBlnO---figure---vhnfp7DaSoydBvnDKtHlhg.png "Authorisation Flow")



# Entity Relationship Diagram


![ERD](/.eraser/FZOdoDP7gyF1Ceo5SjWs___U6y9emfrl8eHDv3I72P3GpFUUJa2___---figure---_YqrANLAr1CktGtRRJqcj---figure---RrGZRELgTlsSRVsgyxw5mw.png "ERD")

# Contributions
To follow the feature branch workflow, create a new branch with the following.

Ensure you are in front of the dev branch to minimise the risk of conflicts: 

```
git pull origin dev
```
Ensure there is a `.gitignore` file so your target folder is not pushed. 

Create a new branch with an appropriate name of the feature you are working on:

```
git checkout -b feature/name
```

After each session or sufficicient changes. 

```
git add .
git commit -m "Commit Message"
```

Once you are ready with your feature pull the latest dev branch. 
```
git checkout dev
git pull origin dev
```
Go back to your own branch and merge dev into your own branch. 

```
git checkout feature/name
git merge dev
```

If there are conflicts please resolve them. 
Once resolved, Login to Github and create a pull request and wait for someone to review it. Once reviewed your branch will be merged into dev.


<!--- Eraser file: https://app.eraser.io/workspace/FZOdoDP7gyF1Ceo5SjWs --->

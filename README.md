# Tinybeans Assessment Project

In order to install the project and start buying products
1. Make sure at least Java v8 is installed
  * `brew tap caskroom/versions && brew cask install java8`
2. Download latest MySQL Instance, make sure it's running on port 3306, add uname `root` and pd `welcome`
* `brew install mysql`
3. Download Play framework v1.4 and install locally
4. Run the `play dependencies` command to download dependencies used in the project
5. Execute the `play run` command to start the server for testing
6. Go to the home page at `http://localhost:9000/products`

---

Unfortunately there is no good way to install v1.4 of Play Framework except manually download tagged version 
* `git clone -b '1.4.5' --single-branch https://github.com/playframework/play1`
* Download ant from package manager `brew install ant`
* Run `ant`in the frameworks/ folder of the git repo.
* Add Play framework to PATH environment variable `export PATH="$PATH:<path to Play>`


Note: DB Schema will be created automatically

Project does not come pre-loaded with any Products, but feel free to upload your own via the application.

First product is on me using the Card with number `4242 4242 4242 4242` Expiration `4/24` CVC `424` 

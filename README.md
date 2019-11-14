# boardgames
By Terry Wang

Angular Setup	1
CLONE BOARDGAMES TO YOUR COMPUTER	1
Switching Branches	2
Pull Requests	3

Angular Setup
Download Node.js if you don’t have it installed (npm package manager will be installed with it by default)
Go through the steps lol
Step 1) if you get this error
Missing write access to /usr/local/lib/node_modules
type the following into the terminal:
sudo chown -R $USER /usr/local/lib/node_modules


CLONE BOARDGAMES TO YOUR COMPUTER
Go to Terry’s repository and click the green clone or download button

Copy the stuff by clicking that thing on the right
Open terminal/console on your computer
cd into the directory you want the boardgames folder to be in
to put boardgames folder type this in the console
git clone <the stuff you copied with no angle brackets yo>
Yay you cloned the stuff now if you push, it will be uploaded on github :D
Make sure to always git pull before every time you code to get all the latest updates on the code
Switching Branches
DO NOT MODIFY THE MASTER BRANCH OR TERRY WILL KICK YOU FROM THE GITHUB

I’ve made 2 branches (originating from master or main branch) on github: 
front-end and back-end (DO NOT FIGHT ME ON THE DASHES IT IS THE CORRECT NAMING CONVENTION)

Before you code, (don’t forget to git pull !!!!) make sure you are in the correct branch. 
To see which branch you’re in, type
git status
It should say something like 
Your branch is up to date with 'origin/<branch name>'


 To switch branches, type
git checkout <name of branch without the angle brackets>

Pull Requests
If you wish to merge your branch into master, you need to create a pull request and get approval from 2 other people.

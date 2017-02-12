**OMNICLAD**
Designed for Discord


**Discord**
Discord is a user friendly voice and text chat designed specifically for gamers. It provides free secure service and works on mobile, desktop, and in web.

 **Omniclad**
 *Omniclad* is a bot designed with user in mind. Gamers are diverse and many, located all over the world. With that in mind, *Omniclad* creators programmed it with multiple functionalities.

**Microsoft Cognitive Services**

 
 **Translation Capabilities**
 Cue in language barriers. Language barriers make for a less pleasant gaming experience. Just imagine trying to play Counter Strike or other games requiring strategy and not being able to communicate with part of you team which speaks Russian/Spanish/Chinese.

You may try to communicate through chat because you don't speak those languages, but by the time you pull out google translate and figure out what was being communicated, you've been shot, the bomb was diffused, and your team lost.


>*Omniclad* is designed to integrate the translating straight into Discord, whether for use between gamers, or between users and tech support.

![Alt text](https://g.gravizo.com/g?
@startuml;
actor Valeriy;
actor Carl;
Valeriy -> Carl: Иди сюда!;
Note right of Carl: Carl translates, time wasted, game lost;
@enduml
)



>With *Omniclad*, the situation would go more like this:



![Alt text](https://g.gravizo.com/g?
@startuml;
actor Valeriy;
participant "Chat" as A;
actor Carl;
activate A;
Valeriy -> A: Иди сюда!;
A -> Carl: Come here!;
Note right of Carl: Carl gets message, helps Valeriy, wins game;
A <- Carl: We won!; 
A -> Valeriy: Мы победили!;
deactivate A;
@enduml
)

**Filter Capabilities**
In chats, often there can be a problem with inappropriate content. There must be a solution to make sure no 12 year olds are scarred for life while gaming. When initialized, the filter feature of *Omniclad* would screen for racy, adult, NSFW content and remove such content from the chat. 


>Filter capability off, any content is able to get into discord, with all sorts of unforeseen effects:

![Alt text](https://g.gravizo.com/g?
@startuml;
actor Troll;
actor Bobby;
Troll -> Bobby: RACY IMAGE;
Note right of Bobby: Bobby, 12 years old, scarred for life;
@enduml
)

>With the filter capability on, any images that are inappropriate are removed from the chat, avoiding catastrophe:


![Alt text](https://g.gravizo.com/g?
@startuml;
actor Troll;
participant "Chat" as A;
participant "Trash" as B;
actor Bobby;
activate A;
Troll -> A: RACY IMAGE;
activate B;
A->B: RACY IMAGE;
Note right of Bobby: Bobby, 12 years old, lives happily ever after;
@enduml
)



> Written with [StackEdit](https://stackedit.io/).

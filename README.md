# IFace

## Design Patterns implementados:
- Command: Implementado na classe CLI. Foi substituido o menu por uma classe controladora para executar as funções, evitando o uso de vários if's.
- Extract Method: Aplicado na classe UserFriends, no método deleteYourUserFriendData. A mesma era um Long Method, havia uma duplicação de código e foi resolvida utilizando uma única função.
- Extract Class: A classe User possuia funções que não deviam estar lá. Foi criada uma nova classe (UserInbox) para separá-las.

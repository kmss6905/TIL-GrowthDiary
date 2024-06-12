## 정규화

### 정규화는 왜 할까
정규화는 왜 배워야할까? 
사실 정규화라고 하는 것을 배우지 않더라도 데이터 모델링을 많이 해보면 경험치에 기반한 직감적 모델링이 가능하다.
이 엔티티를 이렇게 구성하면 나중에 이런 문제가 생긴다 등등. 
간단한 엔티티라면 쉽게 할 수 있곘지만, 복잡한 비지니스라면 직감적 모데링을 하기에는 부족하다. 또한 직감에 근거한 모델링은 근거가 부족한다.

만약 엔티티를 분리해야하는 기준을 알고 있다면, 더 쉽게 데이터 모델링을 할 수 있다. 
개인의 경험치에 기반한 직감적 모델링이 아닌, 근거가 명확한 기준에 의한 모델링은 더 나은 데이터 설계를 가능하게 해준다. 따라서 정규화 작업은 선택이 아닌 
반드시 해야한다. (물론 성능을 요구하는 곳에서는 반정규화를 하기도 한다.)
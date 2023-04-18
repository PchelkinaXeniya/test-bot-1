require:requirements.sc

theme: /
    
    state: Start
        q!: $regex</start>
        a: Здравствуйте!
        script:
            $session = {}
            
    state: WhereToOpenCard 
        q!: * {([$where] $otdelenie) * $open * $debetCard} *
        a:  Сейчас расскажу порядок действий.
        script:
                $response.replies = $response.replies || [];
                $response.replies.push({
                    "type": "timeout",
                    "interval": 6,
                    "targetState": "/ThanksForContacting"
                });
                
    state: NoMatch
        event!: noMatch
        random:
            a: Не смог разобрать :( Попробуете сказать иначе?
            a: Простите, я не понял вас. Давайте попробуем еще раз?
            a: Извините, я не понял. Попробуйте сформулировать по-другому  

    state: ThanksForContacting       
        a: Приятно было пообщаться. Всегда готов помочь вам снова!
        go!: /TheEnd
    
    state: TheEnd
        script:
            $jsapi.stopSession();            
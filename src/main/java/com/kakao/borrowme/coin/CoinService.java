package com.kakao.borrowme.coin;

import com.kakao.borrowme.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CoinService {
    private final CoinJPARepository coinJPARepository;

    public CoinResponse.FindByIdDTO getUserCoin() {
        System.out.println("getUserCoin 호출 완료");
        Optional<Coin> coinOP = coinJPARepository.findByUserId(1L);

        if (coinOP.isPresent()) {
            Coin coin = coinOP.get();
            System.out.println("1번호출");
            return new CoinResponse.FindByIdDTO(coin);
        } else {
            System.out.println("코인 정보가 없습니다.");
            System.out.println("2번호출");
            return null;
        }
    }

    public void useCoin(User user, CoinRequest.UseCoinDTO useCoinDTO) {

        Long totalPrice = useCoinDTO.getTotalPrice();

        // * 예외처리 수정 필요 *
        Optional<Coin> coinOptional = coinJPARepository.findByUserId(user.getId());

        if (coinOptional.isPresent()) {
            Coin coin = coinOptional.get();
            if (coin.getPiece() >= totalPrice) {
                coin.setPiece(coin.getPiece() - totalPrice);
                coinJPARepository.save(coin);
                // 결제 로직 추가
            } else {
                // 코인 잔액이 부족한 경우 예외 처리
                System.out.println("코인이 부족합니다.");
            }
        } else {
            // 코인 엔티티가 없는 경우 예외 처리
            System.out.println("코인 정보가 없습니다.");
        }
    }
}
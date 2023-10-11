package com.kakao.borrowme.coin;

import com.kakao.borrowme._core.security.CustomUserDetails;
import com.kakao.borrowme._core.utils.ApiUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/payment")
public class CoinController {

    private final CoinService coinService;

    @Autowired
    public CoinController(CoinService coinService) {
        this.coinService = coinService;
    }

    // 1. 충전 금액 조회하기
    @GetMapping("")
    public ResponseEntity<?> getUserCoin(@AuthenticationPrincipal CustomUserDetails userDetails) {
        CoinResponse.FindByIdDTO responseDTO = coinService.getUserCoin();
        ApiUtils.ApiResult<?> apiResult = ApiUtils.success(responseDTO);
        return ResponseEntity.ok(apiResult);
    }

}

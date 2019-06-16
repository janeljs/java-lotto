package lotto.dao;

import lotto.domain.LottoResult;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static lotto.domain.Rank.*;

public class LottoResultDAO {
    private final Connection con;

    public LottoResultDAO(Connection connection) {
        this.con = connection;
    }

    public void addLottoResult(String lottoRound, LottoResult lottoResult) throws SQLException {
        String query = "INSERT INTO lottoresult VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pstmt = con.prepareStatement(query);
        pstmt.setString(1, lottoRound);
        pstmt.setString(2, lottoResult.getCountOfRank(FIRST).toString());
        pstmt.setString(3, lottoResult.getCountOfRank(SECOND).toString());
        pstmt.setString(4, lottoResult.getCountOfRank(THIRD).toString());
        pstmt.setString(5, lottoResult.getCountOfRank(FOURTH).toString());
        pstmt.setString(6, lottoResult.getCountOfRank(FIFTH).toString());
        pstmt.setString(7, lottoResult.getEarning().toString());
        pstmt.setString(8, lottoResult.getEarningsRate().toString());
        pstmt.executeUpdate();
    }

    public LottoResultDTO findByLottoRound(String lottoRound) throws SQLException {
        String query = "SELECT * FROM lottoresult WHERE lotto_round = ?";
        PreparedStatement pstmt = con.prepareStatement(query);
        pstmt.setString(1, lottoRound);
        ResultSet rs = pstmt.executeQuery();

        if (!rs.next()) return null;

        LottoResultDTO lottoResultDTO = new LottoResultDTO();
        lottoResultDTO.setLottoRound(rs.getString("lotto_round"));
        lottoResultDTO.setFirstCount(rs.getString("first"));
        lottoResultDTO.setSecondCount(rs.getString("second"));
        lottoResultDTO.setThirdCount(rs.getString("third"));
        lottoResultDTO.setFourthCount(rs.getString("fourth"));
        lottoResultDTO.setFifthCount(rs.getString("fifth"));
        lottoResultDTO.setEarningAmount(rs.getString("winning_amount"));
        lottoResultDTO.setEarningRate(rs.getString("earning_rate"));
        return lottoResultDTO;
    }
}

package hr.tvz.milakovic.hardwareapp.repository;

import hr.tvz.milakovic.hardwareapp.entity.Hardware;
import hr.tvz.milakovic.hardwareapp.entity.HardwareType;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Primary
@Repository
public class JdbcHardwareRepositoryImpl implements HardwareRepository {

    private static final String SELECT_ALL =
            "SELECT code, name, price, type, available FROM hardwares";

    private final JdbcTemplate jdbc;
    private final SimpleJdbcInsert inserter;

    public JdbcHardwareRepositoryImpl(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
        this.inserter = new SimpleJdbcInsert(jdbc)
                .withTableName("hardwares")
                .usingGeneratedKeyColumns("code");
    }

    @Override
    public List<Hardware> findAll() {
        return jdbc.query(SELECT_ALL, this::mapRowToHardware);
    }

    @Override
    public Optional<Hardware> findByCode(String code) {
        try {
            return Optional.ofNullable(
                    jdbc.queryForObject(SELECT_ALL + " WHERE code = ? ", this::mapRowToHardware, code)
            );
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Hardware> save(Hardware hardware) {
        try {
            hardware.setCode(saveHardwareDetails(hardware));
            return Optional.of(hardware);
        } catch (DuplicateKeyException e) {
            return Optional.empty();
        }
    }

    private String saveHardwareDetails(Hardware hardware){
        Map<String, Object> values = new HashMap<>();

        values.put("name", hardware.getName());
        values.put("price", hardware.getPrice());
        values.put("type", hardware.getType());
        values.put("available", hardware.getAvailable());

        return inserter.executeAndReturnKey(values).toString();
    }

    @Override
    public Optional<Hardware> update(String code, Hardware updatedHardware) {
        int executed = jdbc.update("UPDATE hardwares set " +
                "name = ?, " +
                "price = ?, " +
                "type = ?, " +
                "available = ? " +
                " WHERE code = ?",
                updatedHardware.getName(),
                updatedHardware.getPrice(),
                updatedHardware.getType().toString(),
                updatedHardware.getAvailable(),
                updatedHardware.getCode()
        );

        if(executed > 0){
            return Optional.of(updatedHardware);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public void deleteByCode(String code) {
        jdbc.update( "DELETE FROM hardwares WHERE code = ?", code);
    }

    @Override
    public List<Hardware> findBetweenPrices(Double min, Double max) {
        try {
            return jdbc.query(SELECT_ALL + " WHERE price BETWEEN ? AND ?", this::mapRowToHardware, min, max);
        } catch (EmptyResultDataAccessException e) {
            return List.of();
        }
    }

    @Override
    public List<Hardware> findWithString(String pattern) {
        String tempPat = "%" + pattern + "%";
        try {
            return jdbc.query(SELECT_ALL + " WHERE LOWER(name) LIKE LOWER(?)", this::mapRowToHardware, tempPat);
        } catch (EmptyResultDataAccessException e) {
            return List.of();
        }
    }

    private Hardware mapRowToHardware(ResultSet rs, int rowNum) throws SQLException {
        HardwareType tempType = HardwareType.valueOf(rs.getString("type"));
        return new Hardware(
                rs.getString("code"),
                rs.getString("name"),
                rs.getDouble("price"),
                tempType,
                rs.getInt("available")
        );
    }
}

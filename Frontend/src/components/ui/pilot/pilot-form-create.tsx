import { Button, Form, Input, Select } from "antd";
import { FC, useEffect, useState } from "react";
import { PilotDto } from "../../../dtos/pilot-dto";
import { createPilot } from "../../../api/pilots-api";
import { TeamDto } from "../../../dtos/team-dto";
import { Country } from "../../../entities/country";
import { fetchTeams } from "../../../api/team-api";
import { findAllCountries } from "../../../api/country-api";

type FieldType = {
  firstName?: string;
  secondName?: string;
  teamId?: string;
  status?: string;
  number?: number;
  countryId?: string;
};

export interface IPilotFormCreate {
  onSuccess?: () => void;
}

export const PilotFormCreate: FC<IPilotFormCreate> = ({ onSuccess }) => {
  const [form] = Form.useForm();
  const [loading, setLoading] = useState(false);
  const [teams, setTeams] = useState<TeamDto[]>([]);
  const [countries, setCountries] = useState<Country[]>([]);

  useEffect(() => {
    const loadData = async () => {
      try {
        setLoading(true);
        const [teamsData, countriesData] = await Promise.all([
          fetchTeams(),
          findAllCountries(),
        ]);
        setTeams(teamsData);
        setCountries(countriesData);
      } catch (error) {
        console.error("Ошибка:", error);
      } finally {
        setLoading(false);
      }
    };
    loadData();
  }, []);

  const onFinish = async (values: FieldType) => {
    try {
      setLoading(true);
      await createPilot(values as PilotDto);
      form.resetFields();
      if (onSuccess) {
        onSuccess();
      }
    } catch (error) {
      console.error("Ошибка:", error);
    } finally {
      setLoading(false);
    }
  };

  return (
    <Form
      form={form}
      name="basic"
      labelCol={{ span: 8 }}
      wrapperCol={{ span: 16 }}
      style={{ padding: "0 16px", maxWidth: "100%" }}
      initialValues={{ remember: true }}
      onFinish={onFinish}
      autoComplete="off"
    >
      <Form.Item<FieldType>
        label="First name"
        name="firstName"
        rules={[{ required: true, message: "Заполните имя пилота!" }]}
      >
        <Input />
      </Form.Item>

      <Form.Item<FieldType>
        label="Second name"
        name="secondName"
        rules={[{ required: true, message: "Заполните фамилию пилота!" }]}
      >
        <Input />
      </Form.Item>

      <Form.Item<FieldType>
        label="Status"
        name="status"
        rules={[{ required: true, message: "Заполните статус пилота (FIRST, SECOND, RESERVE)!" }]}
      >
        <Input />
      </Form.Item>

      <Form.Item<FieldType>
        label="Team"
        name="teamId"
        rules={[{ required: true, message: "Команда обязательна!" }]}
      >
        <Select
          loading={loading}
          options={teams.map((team) => ({
            label: team.title,
            value: team.id,
          }))}
        />
      </Form.Item>

      <Form.Item<FieldType>
        label="Country"
        name="countryId"
        rules={[{ required: true, message: "Страна обязательна!"}]}
      >
        <Select
          loading={loading}
          options={countries.map((country) => ({
            label: country.title,
            value: country.id,
          }))}
        />
      </Form.Item>

      <Form.Item<FieldType>
        label="Number"
        name="number"
        rules={[
          { required: true, message: "Заполните номер пилота!" },
          {
            validator: (_, value) => {
              if (!value || isNaN(value)) {
                return Promise.reject("Введите правильн6ый номер");
              }
              if (value < 1 || value > 99) {
                return Promise.reject("Номер должен быть от 1 до 99");
              }
              return Promise.resolve();
            },
          },
        ]}
      >
        <Input
          type="number"
          min={1}
          max={99}
          onKeyUp={(e) => !/[0-9]/.test(e.key) && e.preventDefault()}
        />
      </Form.Item>

      <Form.Item label={null}>
        <Button type="primary" htmlType="submit" loading={loading}>
          Submit
        </Button>
      </Form.Item>
    </Form>
  );
};

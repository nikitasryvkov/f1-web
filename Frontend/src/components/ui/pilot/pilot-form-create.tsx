import { Button, Checkbox, Form, FormProps, Input, Select } from "antd";
import { FC, useEffect, useState, useRef  } from "react";
import { PilotDto } from "../../../dtos/pilot-dto";
import { createPilot } from "../../../api/pilots-api";
import { TeamDto } from "../../../dtos/team-dto";
import { Country } from "../../../entities/country";
import { fetchTeams } from "../../../api/team-api";
import { fetchCountry, findAllCountries } from "../../../api/country-api";

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
  
  // const onFinish: FormProps<FieldType>['onFinish'] = async (values) => {
  //   try {
  //     setLoading(true);
  //     await createPilot(values as PilotDto);
  //     form.resetFields();
  //   } catch (error) {
  //   } finally {
  //     setLoading(false);
  //   }
  // };
  
  // const onFinishFailed: FormProps<FieldType>['onFinishFailed'] = (errorInfo) => {
  //   console.log('Failed:', errorInfo);
  // };

  // const [form] = Form.useForm();
  // const [teams, setTeams] = useState<TeamDto[]>([]);
  // const [countries, setCountries] = useState<Country[]>([]);
  // const [loading, setLoading] = useState(false);

  // useEffect(() => {
  //   const loadData = async () => {
  //     try {
  //       setLoading(true);
  //       const [teamsData, countriesData] = await Promise.all([
  //         fetchTeams(),
  //         findAllCountries()
  //       ]);
  //       setTeams(teamsData);
  //       setCountries(countriesData);
  //     } catch (error) {
  //     } finally {
  //       setLoading(false);
  //     }
  //   };
  //   loadData();
  // }, []);

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
            findAllCountries()
          ]);
          setTeams(teamsData);
          setCountries(countriesData);
        } catch (error) {
          console.error('Failed to load data:', error);
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
        console.error('Failed to create pilot:', error);
      } finally {
        setLoading(false);
      }
    };
  
    const onFinishFailed = (errorInfo: any) => {
      console.log('Failed:', errorInfo);
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
        onFinishFailed={onFinishFailed}
        autoComplete="off"
      >
        {/* Form items for firstName, secondName, status, teamId, countryId, number */}
        <Form.Item<FieldType>
          label="First name"
          name="firstName"
          rules={[{ required: true, message: 'Please input your first name!' }]}
        >
          <Input />
        </Form.Item>
  
        <Form.Item<FieldType>
          label="Second name"
          name="secondName"
          rules={[{ required: true, message: 'Please input your second name!' }]}
        >
          <Input />
        </Form.Item>
  
        <Form.Item<FieldType>
          label="Status"
          name="status"
          rules={[{ required: true, message: 'Please input your status!' }]}
        >
          <Input />
        </Form.Item>
  
        <Form.Item<FieldType>
          label="Team"
          name="teamId"
          rules={[{ required: true }]}
        >
          <Select
            loading={loading}
            options={teams.map(team => ({
              label: team.title,
              value: team.id
            }))}
          />
        </Form.Item>
  
        <Form.Item<FieldType>
          label="Country"
          name="countryId"
          rules={[{ required: true }]}
        >
          <Select
            loading={loading}
            options={countries.map(country => ({
              label: country.title,
              value: country.id
            }))}
          />
        </Form.Item>
  
        <Form.Item<FieldType>
          label="Number"
          name="number"
          rules={[
            { required: true, message: 'Please input your number!' },
            {
              validator: (_, value) => {
                if (!value || isNaN(value)) {
                  return Promise.reject('Please enter a valid number');
                }
                if (value < 1 || value > 99) {
                  return Promise.reject('Number must be between 1 and 99');
                }
                return Promise.resolve();
              }
            }
          ]}
        >
          <Input
            type="number"
            min={1}
            max={99}
            onKeyPress={(e) => !/[0-9]/.test(e.key) && e.preventDefault()}
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

// export const PilotFormCreate: FC<IPilotFormCreate> = ({onSuccess }) => {
//   return (
//     <Form
//     name="basic"
//     labelCol={{ span: 8 }}
//     wrapperCol={{ span: 16 }}
//     style={{ padding: "0 16px", maxWidth: "100%"}}
//     initialValues={{ remember: true }}
//     onFinish={onFinish}
//     autoComplete="off"
//   >
//     <Form.Item<FieldType>
//       label="First name"
//       name="firstName"
//       rules={[{ required: true, message: 'Please input your first name!' }]}
//     >
//       <Input />
//     </Form.Item>

//     <Form.Item<FieldType>
//       label="Second name"
//       name="secondName"
//       rules={[{ required: true, message: 'Please input your second name!' }]}
//     >
//       <Input />
//     </Form.Item>

//     <Form.Item<FieldType>
//       label="Status"
//       name="status"
//       rules={[{ required: true, message: 'Please input your status!' }]}
//     >
//       <Input />
//     </Form.Item>

//     <Form.Item<FieldType>
//         label="Team"
//         name="teamId"
//         rules={[{ required: true }]}
//       >
//         <Select
//           loading={loading}
//           options={teams.map(team => ({
//             label: team.title,
//             value: team.id
//           }))}
//         />
//       </Form.Item>

//       <Form.Item<FieldType>
//         label="Country"
//         name="countryId"
//         rules={[{ required: true }]}
//       >
//         <Select
//           loading={loading}
//           options={countries.map(country => ({
//             label: country.title,
//             value: country.id
//           }))}
//         />
//       </Form.Item>

//       <Form.Item<FieldType>
//         label="Number"
//         name="number"
//         rules={[
//           { required: true, message: 'Please input your number!' },
//           { 
//             validator: (_, value) => {
//               if (!value || isNaN(value)) {
//                 return Promise.reject('Please enter a valid number');
//               }
//               if (value < 1 || value > 99) {
//                 return Promise.reject('Number must be between 1 and 99');
//               }
//               return Promise.resolve();
//             }
//           }
//         ]}
//       >
//         <Input 
//           type="number"
//           min={1}
//           max={99}
//           onKeyPress={(e) => !/[0-9]/.test(e.key) && e.preventDefault()}
//         />
//       </Form.Item>

//     <Form.Item label={null}>
//       <Button type="primary" htmlType="submit">
//         Submit
//       </Button>
//     </Form.Item>
//   </Form>
//   )
// }
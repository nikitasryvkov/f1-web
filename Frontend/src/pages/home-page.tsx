import { FC, useEffect, useState } from "react";
import {
  changePosition,
  deletePilot,
  findAllPilots,
  makeBlockedPilot,
  makeUnBlockedPilot,
} from "../api/pilots-api";
import { PilotStatus } from "../entities/pilotStatus";
import { PilotListWidget } from "../components/widgets/pilots-list-widget";
import { PilotDto } from "../dtos/pilot-dto";
import Title from "antd/es/typography/Title";
import { Col, Row, Select } from "antd";
import { PilotFormCreate } from "../components/ui/pilot/pilot-form-create";

export const HomePage: FC = () => {
  const [pilots, setPilots] = useState<PilotDto[]>([]);
  const [sortField, setSortField] = useState<keyof PilotDto>("firstName");
  const [sortOrder, setSortOrder] = useState<"asc" | "desc">("asc");

  useEffect(() => {
    fetchData();
  }, []);

  const fetchData = async () => {
    const data = await findAllPilots();
    setPilots(() => {
      return data;
    });
  };

  const sortedPilots = [...pilots].sort((a, b) => {
    const field = sortField as keyof PilotDto;
    const valueA = a[field];
    const valueB = b[field];

    if (typeof valueA === "string" && typeof valueB === "string") {
      return sortOrder === "asc"
        ? valueA.localeCompare(valueB)
        : valueB.localeCompare(valueA);
    }

    if (valueA < valueB) return sortOrder === "asc" ? -1 : 1;
    if (valueA > valueB) return sortOrder === "asc" ? 1 : -1;
    return 0;
  });

  const onBlocked = async (pilot: PilotDto) => {
    await makeBlockedPilot(pilot.id);
    await fetchData();
  };

  const onUnblocked = async (pilot: PilotDto) => {
    await makeUnBlockedPilot(pilot.id);
    await fetchData();
  };

  const onPosition = async (id: string, status: PilotStatus) => {
    await changePosition(id, status);
    await fetchData();
  };

  const onDelete = async (id: string) => {
    await deletePilot(id);
    await fetchData();
  };

  return (
    <Row gutter={[16, 16]}>
      <Col xs={24} md={16}>
        <>
          <Title style={{ textAlign: "center", padding: "0 16px" }}>
            Managed team
          </Title>

          <Select
            value={sortField}
            onChange={(value) => setSortField(value)}
            style={{ width: 150, marginRight: 8 }}
          >
            <Select.Option value="firstName">Name</Select.Option>
            <Select.Option value="status">Status</Select.Option>
            <Select.Option value="blocked">Blocked</Select.Option>
            <Select.Option value="country">Country</Select.Option>
            <Select.Option value="team">Team</Select.Option>
          </Select>

          <Select
            value={sortOrder}
            onChange={(value) => setSortOrder(value)}
            style={{ width: 100 }}
          >
            <Select.Option value="asc">Asc</Select.Option>
            <Select.Option value="desc">Desc</Select.Option>
          </Select>

          {sortedPilots.length > 0 ? (
            <>
              <PilotListWidget
                pilots={sortedPilots.filter(
                  (p) =>
                    p.status === PilotStatus.FIRST ||
                    p.status === PilotStatus.SECOND
                )}
                lable="Active pilots"
                onUpdate={(pilot) =>
                  pilot.blocked ? onUnblocked(pilot) : onBlocked(pilot)
                }
                onPosition={onPosition}
                onDelete={onDelete}
              />
              <PilotListWidget
                pilots={sortedPilots.filter(
                  (p) => p.status === PilotStatus.RESERVE
                )}
                lable="Reserve pilots"
                onUpdate={(pilot) =>
                  pilot.blocked ? onUnblocked(pilot) : onBlocked(pilot)
                }
                onPosition={onPosition}
                onDelete={onDelete}
              />
            </>
          ) : (
            <div>Loading...</div>
          )}
        </>
      </Col>
      <Col xs={24} md={8}>
        <Title style={{ textAlign: "center", padding: "0 16px" }}>
          Create Pilot
        </Title>
        <PilotFormCreate onSuccess={fetchData} />
      </Col>
    </Row>
  );
};

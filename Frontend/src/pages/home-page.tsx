import { FC, useEffect, useState } from "react";
// import { Pilot } from "../entities/pilot";
import {
  changePosition,
  findAllPilots,
  makeBlockedPilot,
  makeUnBlockedPilot,
} from "../api/pilots-api";
import { PilotStatus } from "../entities/pilotStatus";
import { PilotListWidget } from "../components/widgets/pilots-list-widget";
import { PilotDto } from "../dtos/pilot-dto";
import Title from "antd/es/typography/Title";
import { Col, Row } from "antd";
import { PilotFormCreate } from "../components/ui/pilot/pilot-form-create";

export const HomePage: FC = () => {
  const [pilots, setPilots] = useState<PilotDto[]>([]);

  useEffect(() => {
    fetchData();
  }, []);

  const fetchData = async () => {
    const data = await findAllPilots();
    setPilots(() => {
      return data;
    });
    console.log(data);
  };

  const onBlocked = async (pilot: PilotDto) => {
    console.log(`Pilot ${pilot.id} change block`);
    await makeBlockedPilot(pilot.id);
    await fetchData();
  };

  const onUnblocked = async (pilot: PilotDto) => {
    console.log(`Pilot ${pilot.id} change Unblock`);
    await makeUnBlockedPilot(pilot.id);
    await fetchData();
  };

  const onPosition = async (id: string, status: PilotStatus) => {
    console.log(`Pilot ${id} change Position`);
    console.log(status)
    await changePosition(id, status);
    await fetchData();
  };

  return (
    <Row justify="center" gutter={[16, 16]}>
      <Col xs={24} md={16}>
        <>
          <Title style={{textAlign: "center", padding: "0 16px"}}>Managed team</Title>

          {pilots.length > 0 ? (
            <>
              <PilotListWidget
                  pilots={pilots.filter(
                    (p) =>
                      p.status === PilotStatus.FIRST ||
                      p.status === PilotStatus.SECOND
                  )}
                  lable="Active pilots"
                  onUpdate={(pilot) =>
                    pilot.blocked ? onUnblocked(pilot) : onBlocked(pilot)
                  } onPosition={onPosition}
                />
              <PilotListWidget
                pilots={pilots.filter((p) => p.status === PilotStatus.RESERVE)}
                lable="Reserve pilots"
                onUpdate={(pilot) =>
                  pilot.blocked ? onUnblocked(pilot) : onBlocked(pilot)
                } onPosition={onPosition}
              />
            </>
          ) : (
            <div>Loading...</div>
          )}
        </>
      </Col>
      <Col xs={24} md={8}>
          <Title style={{textAlign: "center", padding: "0 16px"}}> Create Pilot</Title>
          <PilotFormCreate onSuccess={fetchData}/>
      </Col>
    </Row>
  );
};

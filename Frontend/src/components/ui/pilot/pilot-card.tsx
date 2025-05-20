import { FC } from "react";
import styles from "./styles.module.css";
import { PilotStatus } from "../../../entities/pilotStatus";
import { PilotDto } from "../../../dtos/pilot-dto";
import { Button, Card, Divider, Select, Space } from "antd";

export interface IPilotCardProps {
  pilot: PilotDto;
  onStateChange?: (id: string) => void;
  onPosition?: (id: string, status: PilotStatus) => void;
}

export const PilotCard: FC<IPilotCardProps> = ({ pilot, onStateChange, onPosition }) => {
  return (
    <Space>
      <Card className="mob" 
        style={{ 
          backgroundColor: "#ffccc7", 
          textAlign: "center", 
          width: '100%', 
          maxWidth: "250px",
          minWidth: "250px",         
          margin: '0 auto'
        }} 
        title={pilot.team.title}
      >
        <p style={{ margin: "8px 0" }}>{pilot.firstName} {pilot.secondName} {pilot.number}</p>
        <p style={{ margin: "8px 0" }}>
          <PilotStatusCard status={pilot.status} /> {pilot.blocked ? "Blocked" : "Not blocked"}
        </p>
        <p style={{ margin: "8px 0" }}>{pilot.country.title}</p>
        
        <Divider style={{ borderColor: 'red' }}>Change</Divider>
        
        <div style={{ display: "flex", flexWrap: "wrap", gap: 8, justifyContent: "center" }}>
          <Button onClick={() => onStateChange?.(pilot.id)}>
            {pilot.blocked ? "Unblock" : "Block"}
          </Button>
          <Select
            value={pilot.status}
            onChange={(e) => onPosition?.(pilot.id, e as PilotStatus)}
            style={{ minWidth: 120 }}
          >
            {Object.values(PilotStatus).map((status) => (
              <option key={status} value={status}>{status}</option>
            ))}
          </Select>
        </div>
      </Card>
    </Space>
  );
};

export interface IPilotStatusCardProps {
  status: PilotStatus;
}

export const PilotStatusCard: FC<IPilotStatusCardProps> = ({ status }) => {
  const statusToTextMapping: Record<PilotStatus, string> = {
    [PilotStatus.FIRST]: "First",
    [PilotStatus.SECOND]: "Second",
    [PilotStatus.RESERVE]: "Reserve",
  };

  return <>{statusToTextMapping[status]}</>;
};

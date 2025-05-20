import { PilotStatus } from "./pilotStatus";

export type Pilot = {
  id: string;
  firstName: string;
  secondName: string;
  teamId: string;
  status: PilotStatus;
  number: number;
  countryId: string;
  blocked: boolean;
};

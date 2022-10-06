export interface OperationStepCategory {
    id: number,
    name: string,
    shortName: string
}

export interface OperationStepStatus {
    id: number,
    status: string
}

export interface OperationCategory {
    id: number,
    name: string,
    shortName: string
}

export interface Operator {
    id: number,
    operationCategory: OperationCategory
}

export interface OperationStep {
    id: number,
    category: OperationStepCategory
    status: OperationStepStatus,
    start: Date,
    stop: Date,
    duration: Date,
    operator: {
        id: 0,
        operationCategory: {
            id: 0,
            name: string,
            shortName: string
        }
    }
}

export interface OperationStatus {
    id: number,
    status: string
}

export interface OperationData extends Record<string, any> {
    id: 0,
    batchId: string,
    steps: OperationStep[],
    duration: number,
    startTime: Date,
    stopTime: Date,
    status: OperationStatus
}

export interface DataTableProps {
    headerKeyTemplate: HeaderKeyPair[],
    data: Record<string, any>[]

}

export type DataRowType = {
    [key: string]: undefined
}

export type HeaderKeyPair = {
    key: string,
    headerName: string
}
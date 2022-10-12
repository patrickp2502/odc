import { Paper, styled, Table, TableBody, TableCell, TableContainer, TableHead, TableRow } from '@mui/material'
import React from 'react'
import { useNavigate } from 'react-router'
import { OperationDataTableProps, DataRowType, HeaderKeyPair } from '../../../shared/types/interfaces'

const OPERATION_HEADER_KEY_TEMPLATE: HeaderKeyPair[] = [
    { key: "batchId", headerName: "Batch" },
    { key: "startTime", headerName: "Start" },
    { key: "stopTime", headerName: "Ende" },
    { key: "status", headerName: "Status" }
]

const StyledTableHeaderCell = styled(TableCell)({
    backgroundColor: "orange",
    fontWeight: "900",
    color: "white",
})

const StyledTableRow = styled(TableRow)({
    ":hover": {
        "background": "lightgrey",
        "cursor": "pointer"
    }
})

const StyledTableCell = styled(TableCell)({
    fontSize: "1em"
})


const OperationDataTable: React.FC<OperationDataTableProps> = (props) => {
    const navigate = useNavigate()

    return (
        <TableContainer component={Paper} sx={{ maxHeight: "500px" }}>
            <Table stickyHeader>
                <TableHead>
                    <TableRow>
                        {OPERATION_HEADER_KEY_TEMPLATE.map(headerKeyPair =>
                            <StyledTableHeaderCell key={headerKeyPair.headerName}>{headerKeyPair.headerName}</StyledTableHeaderCell>)}
                    </TableRow>
                </TableHead>
                <TableBody>
                    {props.data.map((dataRow: DataRowType) => {
                        return (
                            <StyledTableRow key={dataRow.id} onClick={
                                () => (navigate("/operations/" + dataRow.id))
                            }>

                                {OPERATION_HEADER_KEY_TEMPLATE.map(keyHeaderPair =>
                                    <StyledTableCell key={keyHeaderPair.key}>{dataRow[keyHeaderPair.key]}</StyledTableCell>)}
                            </StyledTableRow>
                        )
                    })
                    }
                </TableBody>
            </Table>
        </TableContainer>
    )
}

export default OperationDataTable